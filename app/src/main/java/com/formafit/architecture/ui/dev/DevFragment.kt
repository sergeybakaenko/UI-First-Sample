package com.formafit.architecture.ui.dev

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.formafit.architecture.R
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class DevFragment : Fragment(), KodeinAware {

    override val kodein: Kodein by kodein()

    val props: DevProps by instance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: com.formafit.architecture.databinding.FragmentDevBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_dev, container, false)

        binding.props = props

        return binding.root
    }
}