package com.kay.progayim

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.kay.progayim.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() , OnBtnClicked {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.e("TAG", " Main ")
        supportFragmentManager.beginTransaction()
            .add(R.id.fragm_cont, FragmentMain())
            .commit()
    }

    override fun goToInfo(id : Long) {
        val fragment = FragmentInfo()
        val bundle = Bundle()
        bundle.putLong("id", id)
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragm_cont,fragment)
            .addToBackStack(null).commit()
    }

}