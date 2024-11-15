package com.example.samsara.screens.onboarding

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.example.samsara.R
import com.example.samsara.databinding.FragmentOnBoardingBinding
import com.example.samsara.databinding.FragmentSavedBinding


class  OnBoardingFragment : Fragment() {
    lateinit var binding: FragmentOnBoardingBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =DataBindingUtil.inflate(layoutInflater,R.layout.fragment_on_boarding,container,false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signinBtn.setOnClickListener {
            if (isNetworkAvailable(requireContext())){
                findNavController().navigate(OnBoardingFragmentDirections.actionOnBoardingFragmentToLoginFragment())

            }else{
                Toast.makeText(requireContext(),"You don't have Internet Connection",Toast.LENGTH_SHORT).show()
            }
        }
        binding.signupButton.setOnClickListener {
            if (isNetworkAvailable(requireContext())){
            findNavController().navigate(OnBoardingFragmentDirections.actionOnBoardingFragmentToSignUpFragment())
            }else{
                Toast.makeText(requireContext(),"You don't have Internet Connection",Toast.LENGTH_SHORT).show()
            }
        }}
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }



}