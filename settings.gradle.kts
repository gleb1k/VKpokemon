pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "VKpokemon"
include(":app")

include(":core")

include(":core:designsystem")
include(":core:navigation")
include(":core:network")
include(":core:presentation")
include(":core:utils")
include(":core:widget")

include(":feature:home:api")
include(":feature:home:internal")
include(":feature:detail:api")
include(":feature:detail:internal")
include(":feature:pokemon:api")
include(":feature:pokemon:internal")
