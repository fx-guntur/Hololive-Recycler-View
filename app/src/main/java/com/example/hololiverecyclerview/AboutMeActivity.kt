package com.example.hololiverecyclerview

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.hololiverecyclerview.databinding.ActivityAboutMeBinding

class AboutMeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAboutMeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAboutMeBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setTitle("About Me")

        binding.btnInstagram.setOnClickListener {
            val linkIg = "https://instagram.com/fx.guntur"
            val igIntent = Intent(Intent.ACTION_VIEW, Uri.parse(linkIg))
            startActivity(igIntent)
        }

        binding.btnLinkedIn.setOnClickListener{
            val linkLinkedin = "https://www.linkedin.com/in/fx-guntur/"
            val linkedinIntent = Intent(Intent.ACTION_VIEW, Uri.parse(linkLinkedin))
            startActivity(linkedinIntent)
        }

        binding.btnGithub.setOnClickListener {
            val linkGithub = "https://github.com/fx-guntur"
            val githubIntent = Intent(Intent.ACTION_VIEW, Uri.parse(linkGithub))
            startActivity(githubIntent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}