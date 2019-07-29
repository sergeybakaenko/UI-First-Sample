package com.forma.fit.ui.screens.playbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.formafit.architecture.R
import com.formafit.architecture.playbook.PlaybookProps
import org.jetbrains.anko.support.v4.selector
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class PlaybookFragment : Fragment(), KodeinAware {

    override val kodein: Kodein by kodein()

    private val props: PlaybookProps by instance()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_playbook, container, false)
        val recyclerView = rootView.findViewById(R.id.recyclerView) as RecyclerView

        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter =
            PlaybookAdapter(context!!, props.scenes.map { it.title }) { scenePosition ->
                selector(
                    "SelectionStatus:",
                    props.scenes[scenePosition].useCases.map { useCase -> useCase.title }) { _, useCasePosition ->
                    props.scenes[scenePosition].useCases[useCasePosition].select()
                }
            }
        return rootView
    }
}
