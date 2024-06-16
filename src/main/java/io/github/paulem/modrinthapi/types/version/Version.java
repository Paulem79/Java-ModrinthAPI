package io.github.paulem.modrinthapi.types.version;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import io.github.paulem.modrinthapi.JsonGetter;
import io.github.paulem.modrinthapi.types.project.ProjectRequestedStatus;
import io.github.paulem.modrinthapi.types.project.ProjectStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public record Version(
        @NotNull String name, @NotNull String versionNumber,
        @Nullable String changelog, @Nullable List<VersionDependency> dependencies,
        @NotNull List<JsonPrimitive> gameVersions, @NotNull VersionType versionType,
        @NotNull List<String> loaders, @NotNull Boolean featured,
        @Nullable ProjectStatus status, @Nullable ProjectRequestedStatus requestedStatus,
        @NotNull String id, @NotNull String projectId, @NotNull String authorId,
        @NotNull String datePublished, @NotNull Integer downloads,
        @Deprecated @Nullable String changelogUrl, @NotNull List<VersionFile> files
        ) {

    public static Version fromJson(JsonElement j) {
        JsonObject json = j.getAsJsonObject();
        JsonGetter getter = new JsonGetter(json);

        return new Version(
                getter.getRequiredString("name"),
                getter.getRequiredString("version_number"),
                getter.getString("changelog"),

                JsonGetter.doOnListElements(
                        json.getAsJsonArray("dependencies"),
                        VersionDependency::fromJson
                ),

                JsonGetter.getOneTypeListFromJsonArray(
                        new JsonPrimitive[0],
                        json.getAsJsonArray("game_versions")
                ),
                VersionType.getEnum(getter.getRequiredString("version_type")),
                JsonGetter.getOneTypeListFromJsonArray(
                        new String[0],
                        json.getAsJsonArray("loaders")
                ),
                getter.getRequiredBoolean("featured"),
                ProjectStatus.getEnum(getter.getString("status")),
                ProjectRequestedStatus.getEnum(getter.getString("requested_status")),

                getter.getRequiredString("id"),
                getter.getRequiredString("project_id"),
                getter.getRequiredString("author_id"),
                getter.getRequiredString("date_published"),
                getter.getRequiredInt("downloads"),
                getter.getString("changelog_url"),
                JsonGetter.doOnListElements(
                        json.getAsJsonArray("files"),
                        VersionFile::fromJson
                )
        );
    }
}