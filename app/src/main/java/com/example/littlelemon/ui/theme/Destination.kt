package com.example.littlelemon.ui.theme

class Destination {
    interface Destinations {
        val home: String
        val profile: String
        val onboarding: String
    }

    object DestinationsImpl : Destinations {
        override val home: String = "home"
        override val profile: String = "profile"
        override val onboarding: String = "onboarding"
    }

}