package sn.delivery.neldam;

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
            .importPackages("sn.delivery.neldam");

        noClasses()
            .that()
            .resideInAnyPackage("sn.delivery.neldam.service..")
            .or()
            .resideInAnyPackage("sn.delivery.neldam.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..sn.delivery.neldam.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
