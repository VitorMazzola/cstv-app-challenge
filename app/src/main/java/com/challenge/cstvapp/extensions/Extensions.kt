package com.challenge.cstvapp.extensions

import android.os.Build
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.challenge.cstvapp.model.domain.MatchDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun <T> Flow<T>.observe(owner: LifecycleOwner, observe: (T) -> Unit) {
    owner.lifecycleScope.launch {
        owner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            this@observe.collect(observe)
        }
    }
}

fun Bundle.getMatchDomain(key: String) : MatchDomain? {
    return if (Build.VERSION.SDK_INT >= 33) {
        this.getSerializable(key, MatchDomain::class.java).let {
             it
        }
    } else {
        this.getSerializable(key).let {
            return it as? MatchDomain
        }
    }
}