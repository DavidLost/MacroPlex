package de.david.macroplex

import de.david.macroplex.settings.*
import javafx.scene.control.Label
import processing.core.PApplet
import tornadofx.*

class SettingsView : View(MyApp.APP_NAME+" Settings") {

    val currentSettings = Settings(PointAmount(), MaxSpeed(), MinDist(), MinSize(), MaxSize(), 1.5f)
    var updateAvailable = false

    lateinit var pointAmountLabel: Label
    lateinit var maxSpeedLabel: Label
    lateinit var minDistLabel: Label
    lateinit var minSizeLabel: Label
    lateinit var maxSizeLabel: Label

    override fun onDock() {

        primaryStage.width = 640.0
        primaryStage.height = 500.0
        primaryStage.isResizable = false
        startSimulation()
    }

    fun startSimulation() {
        val args = arrayOf(
            "--location=0,0",
            MyApp.APP_NAME
        )
        PApplet.runSketch(args, Window(this, currentSettings))
    }

    override val root = vbox(20) {

        paddingVertical = 50
        paddingHorizontal = 120

        hbox {
            label("Point Amount")

            slider(PointAmount.MIN, PointAmount.MAX) {
                value = PointAmount.DEFAULT.toDouble()
                majorTickUnit = max
                prefWidth = 300.0
                //showTickLabelsProperty().setValue(true)
                valueProperty().onChange {
                    currentSettings.pointAmount.value = PApplet.round(it.toFloat())
                    pointAmountLabel.text = currentSettings.pointAmount.value.toString()
                    updateAvailable = true
                }
            }

            label(PointAmount.DEFAULT.toString()) {
                pointAmountLabel = this
            }

        }

        hbox {
            label("Max. Speed")

            slider(MaxSpeed.MIN, MaxSpeed.MAX) {
                value = MaxSpeed.DEFAULT.toDouble()
                majorTickUnit = max
                prefWidth = 300.0
                //showTickLabelsProperty().setValue(true)
                valueProperty().onChange {
                    currentSettings.maxSpeed.value = it.toFloat()
                    updateLabelValue(maxSpeedLabel, currentSettings.maxSpeed.value)
                    updateAvailable = true
                }
            }

            label(MaxSpeed.DEFAULT.toString()) {
                maxSpeedLabel = this
            }
        }

        hbox {
            label("Connecting Distance")

            slider(MinDist.MIN, MinDist.MAX) {
                value = MinDist.DEFAULT.toDouble()
                majorTickUnit = max
                prefWidth = 300.0
                valueProperty().onChange {
                    currentSettings.minDist.value = it.toFloat()
                    updateLabelValue(minDistLabel, currentSettings.minDist.value)
                    updateAvailable = true
                }
            }

            label(MinDist.DEFAULT.toString()) {
                minDistLabel = this
            }
        }

        hbox {
            label("Min. Size")

            slider(1, 32) {
                value = MinSize.DEFAULT.toDouble()
                majorTickUnit = max
                prefWidth = 300.0
                //showTickLabelsProperty().setValue(true)
                valueProperty().onChange {
                    currentSettings.minSize.value = it.toFloat()
                    updateLabelValue(minSizeLabel, currentSettings.minSize.value)
                    updateAvailable = true
                }
            }

            label(MinSize.DEFAULT.toString()) {
                minSizeLabel = this
            }
        }

        hbox {
            label("Max. Size")

            slider(1, 32) {
                value = MaxSize.DEFAULT.toDouble()
                majorTickUnit = max
                prefWidth = 300.0
                //showTickLabelsProperty().setValue(true)
                valueProperty().onChange {
                    currentSettings.maxSize.value = it.toFloat()
                    updateLabelValue(maxSizeLabel, currentSettings.maxSize.value)
                    updateAvailable = true
                }
            }

            label(MaxSize.DEFAULT.toString()) {
                maxSizeLabel = this
            }
        }
    }

    fun updateLabelValue(label: Label, value: Float) {
        var string = String.format("%.2f", value).replace(',', '.')
        if (string.endsWith('0')) string = string.substring(0, string.length-1)
        label.text = string
    }

}
