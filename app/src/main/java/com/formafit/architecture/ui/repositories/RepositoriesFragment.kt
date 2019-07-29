package com.formafit.architecture.ui.repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.formafit.architecture.R
import com.formafit.architecture.databinding.FragmentRepositoriesBinding
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class RepositoriesFragment : Fragment(), KodeinAware {

    override val kodein: Kodein by kodein()

    val props: MutableLiveData<RepositoriesProps> by instance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentRepositoriesBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_repositories, container, false)

        props.observe(viewLifecycleOwner, Observer {
            binding.props = it
        })

        binding.repositoriesRecyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        return binding.root
    }
}
