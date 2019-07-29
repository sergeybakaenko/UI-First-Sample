package com.formafit.architecture

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.reflect.KSuspendFunction0
import kotlin.reflect.KSuspendFunction1

open class Command(private val action: () -> Unit) {

    companion object {
        var coroutine: (KSuspendFunction0<Unit>) -> Unit = { action ->
            GlobalScope.launch(Dispatchers.Main) {
                action()
            }
        }
    }

    constructor(action: KSuspendFunction0<Unit>) : this({
        coroutine(action)
    })

    operator fun invoke() {
        action.invoke()
    }
}

class CoroutineLift<T>(private val value: T, private val action: KSuspendFunction1<T, Unit>) {
    suspend fun run() {
        action(value)
    }
}

fun nop(): Command {
    return Command {}
}

fun <T> nop(): CommandWith<T> {
    return CommandWith {}
}

class CommandWith<T>(private val action: (T) -> Unit) {

    constructor(action2: KSuspendFunction1<T, Unit>) : this({
        Command.coroutine(CoroutineLift(it, action2)::run)
    })

    operator fun invoke(value: T) {
        action.invoke(value)
    }

    fun bind(value: T): Command {
        return Command { action(value) }
    }
}
