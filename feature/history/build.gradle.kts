plugins {
    alias(libs.plugins.onestep.android.feature)
    alias(libs.plugins.onestep.android.library.compose)
}
android {
    namespace = "com.colddelight.history"

}

dependencies {
    implementation (libs.kizitonwose.calendar)
}