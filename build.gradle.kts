plugins {
    id("earth.terrarium.cloche") version "0.10.4"
}

group = "ghana7"
version = "1.1.0"

repositories {
    cloche.librariesMinecraft()

    mavenCentral()

    cloche {
        main()

        mavenFabric()
        mavenNeoforged()
    }
}

cloche {
    minecraftVersion = "1.20.1"

    metadata {
        modId = "cheesemaking_recurdled"
        name = "Cheesemaking Recurdled"
        description = "Lets you make different types of cheese!"
        author("ghana7")
        author("Waterpicker")
        author("Mysticpasta1")
    }

    var core = common("core") {

    }

    neoforge {

        loaderVersion = "47.2.1"

        dependencies {
            include("")
        }

        runs {
            server()
            client()

            data()
        }
    }

    fabric {
        metadata {

            entrypoint("main", "org.example.fabric.FabricExampleMod::initialize")
        }

        data()
        client()

        loaderVersion = "0.16.2"

        dependencies {
            fabricApi("0.90.4") // Optional
        }

        runs {
            server()
            client()
            data()
        }
    }
}