package de.david.macroplex

class Settings(var pointAmount: PointAmount, var speed: Speed, var minDist: MinDist, var minSize: MinSize, var maxSize: MaxSize, var frameSizeFactor: Float) {

    /*companion object {
        val DEFAULT_POINT_AMOUNT = 36
        val DEFAULT_MAX_SPEED = 1.8f
        val DEFAULT_MIN_DIST = 210f
        val DEFAULT_MIN_SIZE = 3f
        val DEFAULT_MAX_SIZE = 12f
        val DEFAULT_FRAME_SIZE_FACTOR = 1.5f
    }*/

    /*var pointAmount by property<Int>(36)
    fun pointAmountProperty() = getProperty(Settings::pointAmount)

    var speed by property<Float>(1.8f)
    fun speedProperty() = getProperty(Settings::speed)

    var minDist by property<Float>(210f)
    fun minDistProperty() = getProperty(Settings::minDist)

    var minSize by property<Float>(3f)
    fun minSizeProperty() = getProperty(Settings::minSize)

    var maxSize by property<Float>(12f)
    fun maxSizeProperty() = getProperty(Settings::maxSize)

    var frameSizeFactor by property<Float>(1.5f)
    fun frameSizeFactorProperty() = getProperty(Settings::frameSizeFactor)*/
}

class PointAmount {
    var value = DEFAULT
    companion object {
        val DEFAULT = 36
        val MIN = 1
        val MAX = 200
    }
}

class Speed {
    var value = DEFAULT
    companion object {
        val DEFAULT = 1.8f
        val MIN = 0f
        val MAX = 10f
    }
}

class MinDist {
    var value = DEFAULT
    companion object {
        val DEFAULT = 210f
        val MIN = 1f
        val MAX = 1000f
    }
}

class MinSize {
    var value = DEFAULT
    companion object {
        val DEFAULT = 3f
        val MIN = 1f
        val MAX = 1000f
    }
}

class MaxSize {
    var value = DEFAULT
    companion object {
        val DEFAULT = 12f
        val MIN = 1f
        val MAX = 1000f
    }
}