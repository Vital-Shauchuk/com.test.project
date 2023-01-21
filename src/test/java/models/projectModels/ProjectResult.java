package models.projectModels;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProjectResult {
    @Expose
    int total;
    @Expose
    int filtered;
    @Expose
    int count;
    @Expose
    List<Project> entities;

}
