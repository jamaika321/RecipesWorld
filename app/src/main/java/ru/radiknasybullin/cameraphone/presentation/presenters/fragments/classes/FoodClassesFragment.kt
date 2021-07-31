package ru.radiknasybullin.cameraphone.presentation.presenters.fragments.classes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.radiknasybullin.cameraphone.R
import ru.radiknasybullin.cameraphone.data.entities.FoodClassesList
import ru.radiknasybullin.cameraphone.presentation.adapter.FoodClassesAdapter

class FoodClassesFragment : Fragment() {

    companion object {
        fun newInstance() = FoodClassesFragment()
    }

    private lateinit var viewModel: FoodClassesViewModel
    private lateinit var rcView : RecyclerView
    private lateinit var adapter : FoodClassesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.food_classes_fragment, container, false)

        val fakeFoodClassList : List<FoodClassesList> = listOf(FoodClassesList(0, " ", " "))

        adapter = FoodClassesAdapter(fakeFoodClassList)
        rcView = rootView.findViewById(R.id.rc_view_food_class)
        rcView.layoutManager = LinearLayoutManager(context)
        rcView.adapter = adapter


        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FoodClassesViewModel::class.java)
        // TODO: Use the ViewModel
    }

    fun setDescription(choosenClass : Int){


    }

}