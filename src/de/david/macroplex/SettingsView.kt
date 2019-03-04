package de.david.macroplex

import javafx.scene.control.Label
import processing.core.PApplet
import tornadofx.*

class SettingsView : View(MyApp.APP_NAME+" Settings") {

    companion object {
        val NO_UPDATE = -1
    }

    val currentSettings = Settings(PointAmount.DEFAULT, SpeedFactor.DEFAULT, ConnectionDistance.DEFAULT, MinSize.DEFAULT, MaxSize.DEFAULT, 1.5f)
    var updateState = NO_UPDATE

    lateinit var pointAmountLabel: Label
    lateinit var speedFactorLabel: Label
    lateinit var connectionDistanceLabel: Label
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
                    currentSettings.pointAmount = PApplet.round(it.toFloat())
                    pointAmountLabel.text = currentSettings.pointAmount.toString()
                    updateState = 0
                }
            }

            label(PointAmount.DEFAULT.toString()) {
                pointAmountLabel = this
            }

        }

        hbox {
            label("SpeedFactor")

            slider(SpeedFactor.MIN, SpeedFactor.MAX) {
                value = SpeedFactor.DEFAULT.toDouble()
                majorTickUnit = max
                prefWidth = 300.0
                //showTickLabelsProperty().setValue(true)
                valueProperty().onChange {
                    currentSettings.speedFactor = it.toFloat()
                    updateLabelValue(speedFactorLabel, currentSettings.speedFactor)
                }
            }

            label(SpeedFactor.DEFAULT.toString()) {
                speedFactorLabel = this
            }
        }

        hbox {
            label("Connecting Distance")

            slider(ConnectionDistance.MIN, ConnectionDistance.MAX) {
                value = ConnectionDistance.DEFAULT.toDouble()
                majorTickUnit = max
                prefWidth = 300.0
                valueProperty().onChange {
                    currentSettings.connectionDistance = it.toFloat()
                    updateLabelValue(connectionDistanceLabel, currentSettings.connectionDistance)
                }
            }

            label(ConnectionDistance.DEFAULT.toString()) {
                connectionDistanceLabel = this
            }
        }

        hbox {
            label("Min. Size")

            slider(MinSize.MIN, MinSize.MAX) {
                value = MinSize.DEFAULT.toDouble()
                majorTickUnit = max
                prefWidth = 300.0
                //showTickLabelsProperty().setValue(true)
                valueProperty().onChange {
                    currentSettings.minSize = it.toFloat()
                    updateLabelValue(minSizeLabel, currentSettings.minSize)
                    updateState = 1
                }
            }

            label(MinSize.DEFAULT.toString()) {
                minSizeLabel = this
            }
        }

        hbox {
            label("Max. Size")

            slider(MaxSize.MIN, MaxSize.MAX) {
                value = MaxSize.DEFAULT.toDouble()
                majorTickUnit = max
                prefWidth = 300.0
                //showTickLabelsProperty().setValue(true)
                valueProperty().onChange {
                    currentSettings.maxSize = it.toFloat()
                    updateLabelValue(maxSizeLabel, currentSettings.maxSize)
                    updateState = 1
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
