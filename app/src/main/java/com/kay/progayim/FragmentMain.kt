package com.kay.progayim

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kay.progayim.database.Characters
import com.kay.progayim.databinding.FragmMainBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class FragmentMain : Fragment(R.layout.fragm_main) {
    private var binding1 : FragmMainBinding? = null
    private val binding get() = binding1!!

    private val dbInstance get() = Injector.database
    private lateinit var listener : OnBtnClicked
    private val character get() = Injector.rickandmortyApi
    private lateinit var adapter : CharacterAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as OnBtnClicked
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding1 = FragmMainBinding.bind(view)

        val layoutManager = LinearLayoutManager(activity)
        adapter = CharacterAdapter {
            Log.e("TAG", "Main Frg OK $it")
            listener.goToInfo(it)
        }

        binding.apply {
            recycler.layoutManager = layoutManager
            recycler.adapter = adapter
            recycler.addItemDecoration(DividerItemDecoration(activity, RecyclerView.VERTICAL))
            refresh()
            swipeRefreshLayout.setOnRefreshListener {
                refresh()
                Toast.makeText(context,"Ok ",Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun refresh() {
        character.getAll()
            .subscribeOn(Schedulers.io())
            .map {
                val listCh = mutableListOf<Characters>()
                it.results.forEach {
                    val characters = Characters(
                        id = it.id,
                        name = it.name,
                        status = it.status,
                        species = it.species,
                        type = it.type,
                        gender = it.gender,
                        location = it.location.name,
                        image = it.image,
                        created = it.created,
                        url = it.url
                    )
                    listCh.add(characters)
                }
                listCh.toList()
                dbInstance.characterDao().insert(listCh)
                it.results
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext{
                adapter.setData(it)
                Log.e("TAG", "fragmentMain doOnSuccess ")
            }
            .doOnError {
                Log.e(
                    "TAG", "fragmentMain doOnError getById ${Thread.currentThread().name}"
                )
            }
            .doFinally{
                binding.swipeRefreshLayout.isRefreshing = false
            }
            .subscribe()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding1 = null
    }
}