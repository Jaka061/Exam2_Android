package com.kay.progayim

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.kay.progayim.databinding.FragmInfoBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FragmentInfo : Fragment(R.layout.fragm_info) {
    private var binding1 : FragmInfoBinding? = null
    private val binding get() = binding1!!

    private lateinit var listener : OnBtnClicked
    private val ch get() = Injector.rickandmortyApi

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnBtnClicked
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        binding1 = FragmInfoBinding.bind(view)
        val id = arguments?.getLong("id")!!

        binding.apply {
                ch.getById(id)
                    .subscribeOn(Schedulers.io())
                    .map { it }
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSuccess {
                        val img = image
                        Glide.with(requireActivity()).load(it.image).into(img)
                        rName.text = "Name: ${it.name}"
                        rStatus.text = "Status: ${it.status}"
                        rSpecies.text = "Species: ${it.species}"
                        rGender.text = "Gender: ${it.gender}"
                        rUrl.text = "Url: ${it.url}"
                        rCreated.text = "Created: ${it.created}"
                        Log.e("TAG", "Info - ${it.type}")
                    }
                    .subscribe()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding1 = null
    }
}