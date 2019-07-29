package com.formafit.architecture.playbook

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import com.forma.fit.ui.screens.playbook.PlaybookFragmentDirections
import com.formafit.architecture.Command
import com.formafit.architecture.R
import com.formafit.architecture.playbook.scene.RepositoriesScene
import com.formafit.architecture.ui.repositories.RepositoriesProps
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.provider

class PlaybookProps(val scenes: List<PlaybookActivity.PlaybookScene>)

class PlaybookActivity : AppCompatActivity(), KodeinAware {

    class PlaybookScene(val title: String, val useCases: ArrayList<UseCase>) {
        class UseCase(val title: String, val select: Command)
    }

    private val repositoriesScene = RepositoriesScene()

    private val playbookProps = PlaybookProps(
        listOf(
            PlaybookScene(
                "Repositories", arrayListOf(
                    PlaybookScene.UseCase("Empty", Command {
                        repositoriesScene.showEmpty()
                        openRepositories()
                    }),
                    PlaybookScene.UseCase("Loading", Command {
                        repositoriesScene.showLoading()
                        openRepositories()
                    }),
                    PlaybookScene.UseCase("Loaded", Command {
                        repositoriesScene.showLoaded()
                        openRepositories()
                    }),
                    PlaybookScene.UseCase("Loading-Empty-Loading-Loaded", Command {
                        repositoriesScene.showLoading()
                        openRepositories()
                        GlobalScope.launch(Dispatchers.Main) {
                            delay(3000)
                            repositoriesScene.showEmpty()
                            delay(3000)
                            repositoriesScene.showLoading()
                            delay(3000)
                            repositoriesScene.showLoaded()
                        }
                    })
                )
            )
        )
    )

    override var kodein: Kodein = Kodein.lazy {
        bind<PlaybookProps>() with provider { playbookProps }
        bind<MutableLiveData<RepositoriesProps>>() with provider { repositoriesScene.props }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playbook)
    }

    private fun openRepositories() {
        val action = PlaybookFragmentDirections.openRepositories()
        Navigation.findNavController(this, R.id.playbookHostFragment).navigate(action)
    }
}