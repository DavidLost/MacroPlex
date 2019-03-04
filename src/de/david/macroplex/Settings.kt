package de.david.macroplex

class Settings(var pointAmount: Int, var speedFactor: Float, var connectionDistance: Float, var minSize: Float, var maxSize: Float, var frameSizeFactor: Float)

abstract class PointAmount {
    companion object {
        val DEFAULT = 36
        val MIN = 0
        val MAX = 300
    }
}

abstract class SpeedFactor {
    companion object {
        val DEFAULT = 1.8f
        val MIN = 0f
        val MAX = 12f
    }
}

abstract class ConnectionDistance {
    companion object {
        val DEFAULT = 210f
        val MIN = 1f
        val MAX = 1000f
    }
}

class MinSize {
    companion object {
        val DEFAULT = 3f
        val MIN = 0f
        val MAX = 128f
    }
}

class MaxSize {
    companion object {
        val DEFAULT = 12f
        val MIN = 0f
        val MAX = 128f
    }
}