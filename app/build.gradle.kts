plugins {
      alias(libs.plugins.android.application)
}

android {
      namespace = "com.csds.transactiondissplay"
      compileSdk = 35

      defaultConfig {
	    applicationId = "com.csds.transactiondissplay"
	    minSdk = 29
	    targetSdk = 34
	    versionCode = 1
	    versionName = "1.0"

	    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
      }

      buildTypes {
	    release {
		  isMinifyEnabled = false
		  proguardFiles(
			getDefaultProguardFile("proguard-android-optimize.txt"),
			"proguard-rules.pro"
		  )
	    }
      }
      buildFeatures{
	    dataBinding=true
      }
      compileOptions {
	    sourceCompatibility = JavaVersion.VERSION_1_8
	    targetCompatibility = JavaVersion.VERSION_1_8
      }
}

dependencies {

      implementation(libs.appcompat)
      implementation(libs.material)
      implementation(libs.activity)
      implementation(libs.constraintlayout)
      testImplementation(libs.junit)
      androidTestImplementation(libs.ext.junit)
      androidTestImplementation(libs.espresso.core)

      //shared prefss and bio
     implementation(libs.security.crypto)
      implementation(libs.biometric)
      //apicalls
      implementation( libs.retrofit)
      implementation (libs.converter.gson)
      //simple widget for refreshing after swiping up or down this is extra bonus feature i ad, dark mode is present but sometimes text dont whow correct
      implementation (libs.swiperefreshlayout);
      //bonus feature room
      implementation(libs.room.runtime)
      annotationProcessor( "androidx.room:room-compiler:2.7.0")
}