package models;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TestCase {
    @Expose
    int id;
    @Expose
    String title;
    @Expose
    String description;
    @Expose
    String preconditions;
    @Expose
    String postconditions;
    @Expose
    String severity;
    @Expose
    String priority;
    @Expose
    String type;
    @Expose
    String behavior;
    @Expose
    String automationStatus;
    @Expose
    String status;
    @Expose
    String milestone;
    @Expose
    String suite;
    @Expose
    String layer;
    @Expose
    String isFlaky;
}
