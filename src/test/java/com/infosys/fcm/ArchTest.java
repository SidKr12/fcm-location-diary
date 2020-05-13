package com.infosys.fcm;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.infosys.fcm");

        noClasses()
            .that()
            .resideInAnyPackage("com.infosys.fcm.service..")
            .or()
            .resideInAnyPackage("com.infosys.fcm.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.infosys.fcm.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
