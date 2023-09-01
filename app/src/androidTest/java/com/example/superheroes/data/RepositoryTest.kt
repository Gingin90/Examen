package com.example.superheroes.data

import android.content.Context
import android.telephony.TelephonyManager
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.superheroes.data.local.SuperHeroDao
import com.example.superheroes.data.local.SuperHeroDatabase
import com.example.superheroes.data.local.SuperHeroEntity
import com.google.common.annotations.VisibleForTesting
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

class RepositoryTest
@get:Rule
var instantExecutorRule = InstantTaskExecutorRule()
private lateinit var superheroDao: SuperHeroDao
private lateinit var db: SuperHeroDatabase

@Before
fun setUp() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    db = Room.inMemoryDatabaseBuilder(context, SuperHeroDatabase::class.java).build()
    superheroDao = db.getSuperHeroesDAO()
}
@After
fun tearDown() {
    db.close()
}
@Test
fun insertPhone_hapSipyCase_1element() = runBlocking {
    // Given
    val superHeroeData = SuperHeroEntity(123,"nombre", "origen", "imagen","poder",2000)
    val listSuperHeroData:MutableList<SuperHeroEntity>?=null
    listSuperHeroData?.add(superHeroeData)
    // When
    superheroDao.insertSuperHeroes(listSuperHeroData)
    // Then
    superheroDao.getSuperHeroes().observeForever {
        Truth.assertThat(it).isNotNull()
        Truth.assertThat(it).isNotEmpty()
        Truth.assertThat(it).hasSize(1)
    }
}

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserve: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(value: T) {
            data = value
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)
    try {
        afterObserve.invoke()
        if (!latch.await(time, timeUnit)) {
            throw TelephonyManager.TimeoutException("LiveData value was never set.")
        }
    } finally {
        this.removeObserver(observer)
    }
    @Suppress("UNCHECKED_CAST")
    return data as T
}