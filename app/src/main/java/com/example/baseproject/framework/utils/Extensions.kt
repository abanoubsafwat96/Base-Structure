package com.example.baseproject.framework.utils

import com.example.baseproject.business.entities.Profile
import kotlin.random.Random

object Extensions {
    fun List<Profile>.getOneRandom(): Profile {
        val randomIndex = Random.nextInt(this.size);
        return this[randomIndex]
    }
}