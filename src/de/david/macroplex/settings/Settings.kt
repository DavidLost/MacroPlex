package de.david.macroplex.settings

import sun.plugin2.os.windows.FLASHWINFO

class PointAmount {
    var value = DEFAULT
    companion object {
        val DEFAULT = 36
        val MIN = 1
        val MAX = 200
    }
}

class MaxSpeed {
    var value = DEFAULT
    companion object {
        val DEFAULT = 1.8f
        val MIN = 0.1
        val MAX = 10
    }
}

class MinDist {
    var value = PointAmount.DEFAULT
    companion object {
        val DEFAULT = 210f
        val MIN = 1
        val MAX = 1000
    }
}

class MinSize {
    var value = PointAmount.DEFAULT
    companion object {
        val DEFAULT = 3f
        val MIN = 1
        val MAX = 1000
    }
}

class MaxSize {
    var value = PointAmount.DEFAULT
    companion object {
        val DEFAULT = 12f
        val MIN = 1
        val MAX = 1000
    }
}