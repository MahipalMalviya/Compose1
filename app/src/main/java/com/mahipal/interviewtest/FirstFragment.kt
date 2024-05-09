package com.mahipal.interviewtest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mahipal.interviewtest.databinding.FragmentFirstBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.login.setOnClickListener {
            postLogin()

        }
    }

    private fun postLogin() {
        RetrofitClient.apiService.login(getLoginData()).enqueue(object :Callback<Any> {
            override fun onResponse(call: Call<Any>, response: Response<Any>) {
                if (response.code() == 200) {
                    findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
                }
            }

            override fun onFailure(call: Call<Any>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }

    private fun getLoginData(): HashMap<String, String> {
        return hashMapOf(Pair("email", binding.rtUserName.text.toString().trim()),
            Pair("password",binding.rtPassword.text.toString().trim()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}