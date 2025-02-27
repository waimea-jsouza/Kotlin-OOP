/**
 * Intro to OOP looking at the creation
 * and use of classes and objects
 */


/**
 * The main() method is the entry point for
 * any Kotlin program
 */
fun main() {
    // Let's make some cats
    val cat1 = Cat("Jimmy")
    val cat2 = Cat("Dave", 1)
    val cat3 = Cat("Nigel")

    println(cat1)
    println(cat2)
    println(cat3)

    // Play with the cats
    cat1.poke()
    cat1.stroke()
    cat1.stroke()
    cat1.poke()

    // Manipulate the cat friendships
    println(cat1)
    cat1.makeFriends(cat3)
    println(cat1)
    println(cat3)

    //-----------------------------------------

    // Let's make a few rooms
    println("------------------------")

    val bedroom = Room("Bedroom", 3, 4, 5)
    val kitchen = Room("Kitchen", 3, 5, 6)

    // And some windows
    println("------------------------")

    val win1 = Window(1, 3)
    val win2 = Window(1, 2)
    val win3 = Window(1, 5)
    val win4 = Window(1, 30)

    // Try to add the windows to the rooms
    println("------------------------")

    bedroom.addWindow(win1)
    bedroom.addWindow(win2)
    bedroom.addWindow(win3)
    bedroom.addWindow(win4)

    // See how the rooms look afterwards
    println("------------------------")

    println(bedroom.info())
}


/**
 * Cat class, tracking name and other key data
 * Cats can be woken, stroked, make friends, etc.
 */
class Cat(val name: String, var legs: Int = 4) {
    var isSleeping: Boolean = false;
    var isHungry: Boolean = false;
    var bff: Cat? = null

    // Method that updates state
    fun poke() {
        if (isSleeping) {
            isSleeping = false
            println("You woke up $name!")
        }
        else {
            println("$name is angry and scratches you")
        }
    }

    // Method that updates state
    fun stroke() {
        println("$name purrrrrrrrrrs...")

        if (!isSleeping) {
            isSleeping = true
            println("$name falls asleep")
        }
    }

    // Method where data is supplied
    fun makeFriends(newFriend: Cat) {
        bff = newFriend         // Connect to them from us
        newFriend.bff = this    // Connect back to us ('this')

        println("$name is now friends with ${newFriend.name}")
    }

    // This method is run when an object is created
    init {
        println("Creating... \n  Cat, $name \n")
    }

    // This replaces the default function that prints
    // the object's ID with something more useful
    override fun toString(): String {
        var info: String = ""

        info += "Name: $name\n"
        info += "Legs: $legs\n"
        info += "Hungry: $isHungry\n"
        info += "Sleeping: $isSleeping\n"
        if (bff != null) {
            info += "Bff: ${bff!!.name}"
        }
        else {
            info += "No BFF!"
        }

        return info
    }
}


/**
 * Room class defining a physical room with h, w, d
 */
class Room(
    val name: String,
    val height: Int,
    val width: Int,
    val depth: Int
) {
    val windows = mutableListOf<Window>()
    val doors = mutableListOf<Door>()

    init {
        println("Creating... \n  ${info()} \n")
    }

    fun volume(): Int {
        // Return the volume of the room = h*w*l
        return height * width * depth
    }

    fun area(): Int {
        val walls = width * height * 2 + depth * height * 2
        val floor = width * depth
        val ceiling = floor
        var area = walls + floor + ceiling

        for (window in windows) {
            area -= window.area()
        }

        for (door in doors) {
            area -= door.area()
        }

        return area
    }

    fun info(): String {
        var info = ""

        info += "Room: $name \n"
        info += "Dimensions: ${height}m tall, ${width}m wide, ${depth}m deep \n"
        info += "Windows: \n"

        for (window in windows) {
            info += " - ${window.info()} \n"
        }

        info += "Doors: \n"

        for (door in doors) {
            info += " - ${door.info()} \n"
        }

        info += "Volume: ${volume()}m³ \n"
        info += "Area: ${area()}m²"

        return info
    }

    fun maxDimension(): Int {
        return if (width > depth) width else depth
    }

    fun addWindow(newWindow: Window) {
        if (newWindow.width <= maxDimension() && newWindow.height <= height) {
            println("Adding ${newWindow.info()} to $name")
            windows.add(newWindow)
        }
        else {
            println("ERROR: Room dimensions don't allow ${newWindow.info()}")
        }
    }
}

/**
 * Class that defines a window which can be added to a Room
 */
class Window(val height: Int = 1, val width: Int = 1) {
    init {
        println("Creating... \n  ${info()} \n")
    }

    fun area(): Int {
        return width * height
    }

    fun info(): String {
        return "Window: ${height}m high x ${width}m wide, area:${area()}m²"
    }
}

/**
 * Class that defines a door that can be added to a Room
 */
class Door(val height: Int = 2, val width: Int = 1) {
    init {
        println("Creating... \n  ${info()} \n")
    }

    fun area(): Int {
        return width * height
    }

    fun info(): String {
        return "Door: ${height}m high x ${width}m wide, area:${area()}m²"
    }
}
