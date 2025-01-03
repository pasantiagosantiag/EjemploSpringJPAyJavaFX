plugins {
    id("java")
    id("org.springframework.boot") version "3.4.1"
    id("io.spring.dependency-management") version "1.1.7"
    //javafx
    id("org.openjfx.javafxplugin") version "0.1.0"
    application
}

group = "ies.accesodatos"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
javafx {
    version = "22.0.1"
    modules = listOf( "javafx.controls", "javafx.graphics", "javafx.fxml", "javafx.media", "javafx.swing" )
}
dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    //spring data jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    //javafx
    //javafx
    implementation("io.github.palexdev:materialfx:11.17.0")
    implementation("org.controlsfx:controlsfx:11.2.1")
    implementation("org.kordamp.bootstrapfx:bootstrapfx-core:0.4.0")
    implementation("fr.brouillard.oss:cssfx:11.5.1")
    implementation("com.dlsc.formsfx:formsfx-core:11.6.0")
    implementation("com.dlsc.formsfx:formsfx:11.6.0")
    implementation("org.kordamp.ikonli:ikonli-javafx:12.3.1")
    implementation("org.kordamp.ikonli:ikonli-antdesignicons-pack:12.3.1")
}

tasks.test {
    useJUnitPlatform()
}
application {
    // Define the main class for the application.
    mainClass = "ies.accesodatos.Main"
}