package de.david.macroplex

import de.david.macroplex.gui.SettingsView
import tornadofx.App

class MyApp : App(SettingsView::class) {
    companion object {
        val APP_NAME = "Macro Plex"
    }
}