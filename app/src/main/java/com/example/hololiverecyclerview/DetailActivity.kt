package com.example.hololiverecyclerview

import android.app.TaskStackBuilder
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.app.NavUtils
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.hololiverecyclerview.databinding.ActivityDetailBinding
import com.example.hololiverecyclerview.databinding.ActivityMainBinding
import com.example.hololiverecyclerview.model.Member

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailBinding.inflate((layoutInflater))
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val member = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Member>(EXTRA_MEMBER, Member::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Member>(EXTRA_MEMBER)
        }

        if (member != null) {
            Glide.with(binding.ivProfile.context).load(member.photo).into(binding.ivProfile)
            binding.tvName.text = member.name
            binding.tvRegion.text = member.region
            binding.tvDesc.text = member.desc
            binding.tvDate.text = member.debutDate
            binding.tvGeneration.text = member.generation
        }

        binding.actionShare.setOnClickListener{
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Do you know ${member?.name} She is member of ${member?.generation}")
            startActivity(Intent.createChooser(shareIntent, "Share via"))
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

    companion object {
        const val EXTRA_MEMBER = "extra_member"
    }
}