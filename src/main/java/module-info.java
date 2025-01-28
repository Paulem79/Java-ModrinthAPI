module modrinthapi {
    requires com.google.gson;
    requires org.jetbrains.annotations;

    exports ovh.paulem.modrinthapi;
    exports ovh.paulem.modrinthapi.types.enums;
    exports ovh.paulem.modrinthapi.types.classes;
    exports ovh.paulem.modrinthapi.types.classes.deprecated;
    exports ovh.paulem.modrinthapi.types.project;
    exports ovh.paulem.modrinthapi.types.version;
}