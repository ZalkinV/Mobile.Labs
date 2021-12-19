package com.itmo.basiclayout.task3.company.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.itmo.basiclayout.R
import com.itmo.basiclayout.databinding.ActivityTask3Binding
import com.itmo.basiclayout.task3.company.Consts
import com.itmo.basiclayout.task3.company.domain.CompanyEntity
import com.itmo.basiclayout.task3.company.domain.CompanyInteractor
import com.itmo.basiclayout.task3.company.domain.CompanyInteractorImpl

class Task3Activity : AppCompatActivity() {

    companion object {
        private const val LOG_TAG = "TASK3"
    }

    private lateinit var binding: ActivityTask3Binding

    private val companyInteractor: CompanyInteractor = CompanyInteractorImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTask3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeListeners()
    }

    private fun initializeListeners() = with(binding) {
        t3BtnLoad.setOnClickListener {
            try {
                val company = companyInteractor.get(Consts.COMPANY_ID_TO_LOAD)
                displayCompanyInfo(company)
            }
            catch (e: Exception) {
                val message = e.message ?: "Error occured"

                Toast.makeText(baseContext, message, Toast.LENGTH_SHORT).show()
                Log.e(LOG_TAG, message)
            }
        }
    }

    private fun displayCompanyInfo(company: CompanyEntity) = with(binding) {
        t3TvName.text = company.name
        t3TvDescription.text = company.description
    }

}
