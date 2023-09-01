package com.example.superheroes.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.superheroes.R
import com.example.superheroes.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {
    private var superHeroId: String? = null
    lateinit var binding: FragmentDetailBinding
    private val viewModel: SuperHeroViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            superHeroId = it.getString("id")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        initComponents()
        initListeners()
        return binding.root
    }

    private fun initComponents() {
        viewModel.superHeroDetailsViewModel(superHeroId.toString().toInt())

        viewModel.superHeroDetailLiveData(superHeroId.toString().toInt())
            .observe(viewLifecycleOwner) {
                if (it != null) {
                    binding.imageViewImageDetail.load(it.imageUrl)
                    binding.tvNameDetail.text = it.name
                    binding.tvOriginDetail.text = it.origin
                    binding.tvYearDetail.text = it.year.toString()
                    binding.tvSuperPowerDetail.text = it.superPower
                    binding.tvColorDetail.text = it.color
                    if (!it.translate) {
                        binding.tvTranslateDetail.text = getString(R.string.translate_false)
                    } else {

                        binding.tvTranslateDetail.text = getString(R.string.translate_true)
                    }
                }
            }
    }

    private fun initListeners() {
        viewModel.superHeroDetailLiveData(superHeroId.toString().toInt())
            .observe(viewLifecycleOwner) {
                if (it != null) {
                    val asunto = getString(R.string.subject_msn, it.name)
                    val message = getString(R.string.body_msn, it.name)
                    val mail = getString(R.string.addressee_msn)

                    binding.btnCorreo.setOnClickListener {
                        val intentMail = Intent(Intent.ACTION_SEND, Uri.parse(mail))
                        intentMail.type = "text/plain"
                        intentMail.putExtra(Intent.EXTRA_EMAIL, arrayOf(mail))
                        intentMail.putExtra(Intent.EXTRA_SUBJECT, asunto)
                        intentMail.putExtra(Intent.EXTRA_TEXT, message)
                        startActivity(Intent.createChooser(intentMail, "Send Mail"))
                    }
                    findNavController().navigate(R.id.action_detailFragment_to_listFragment)
                }
            }
    }}