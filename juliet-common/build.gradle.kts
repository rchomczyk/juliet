plugins {
    `juliet-java`
    `juliet-publish`
    `juliet-unit-test`
    `juliet-repositories`
}

dependencies {
    api(libs.hikaricp)
    compileOnly(libs.annotations)
}

julietPublish {
    artifactId = "juliet"
}