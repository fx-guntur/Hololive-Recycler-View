package com.example.hololiverecyclerview

import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hololiverecyclerview.adapter.ListMemberAdapter
import com.example.hololiverecyclerview.model.Member

class MainActivity : AppCompatActivity() {
    private lateinit var rvMember: RecyclerView
    private val list = ArrayList<Member>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMember = findViewById(R.id.rv_hololive)
        rvMember.setHasFixedSize(true)

        list.addAll(getListHeroes())
        showRecyclerList()
    }

    private fun getListHeroes(): ArrayList<Member> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataRegion = resources.getStringArray(R.array.data_region)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val dataGen = resources.getStringArray(R.array.data_generation)
        val dataDebut = resources.getStringArray(R.array.data_debut)
        val listHero = ArrayList<Member>()
        for (i in dataName.indices) {
            val hero = Member(dataName[i], dataRegion[i],dataDescription[i], dataPhoto[i], dataGen[i], dataDebut[i])
            listHero.add(hero)
        }
        return listHero
    }

    private fun showRecyclerList() {
        rvMember.layoutManager = LinearLayoutManager(this)
        val listMemberAdapter = ListMemberAdapter(list)
        rvMember.adapter = listMemberAdapter

        listMemberAdapter.setOnItemClickCallback(object : ListMemberAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Member) {
                showSelectedHero(data)
                val moveWithObjectIntent = Intent(this@MainActivity, DetailActivity::class.java)
                moveWithObjectIntent.putExtra(DetailActivity.EXTRA_MEMBER, data)
                startActivity(moveWithObjectIntent)
            }
        })
    }

    private fun showSelectedHero(member: Member) {
        Toast.makeText(this, "You choose " + member.name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page -> {
                val moveIntent = Intent(this@MainActivity, AboutMeActivity::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}