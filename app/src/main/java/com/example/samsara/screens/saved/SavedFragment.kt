package com.example.samsara.screens.saved

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.samsara.MainActivity
import com.example.samsara.R
import com.example.samsara.adapters.SavedRecycleAdapter
import com.example.samsara.databinding.FragmentSavedBinding
import com.example.samsara.datamodel.ApartmentDataModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SavedFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SavedFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentSavedBinding
    private lateinit var viewModel: SavedViewModel
   lateinit var adapter:SavedRecycleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_saved, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity =activity as MainActivity

        val viewModelFactory=SavedViewModelFactory(requireActivity().application)
        viewModel=ViewModelProvider(this,viewModelFactory)[SavedViewModel::class.java]
        adapter=SavedRecycleAdapter(activity){
            CoroutineScope(Dispatchers.IO).launch{
                viewModel.deleteItem(it)
                adapter.data=viewModel.ApartmentList.value!!
            }
            Toast.makeText(requireContext(),"deleted",Toast.LENGTH_SHORT).show()
        }
        viewModel.ApartmentList.observe(viewLifecycleOwner){
            setUpRecycle(it)
        }
    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.setRoom()
        }

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SavedFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SavedFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun setUpRecycle(list: List<ApartmentDataModel>) {
        adapter.data=list
        binding.savedRecycleView.adapter=adapter

    }
}
