package cn.edu.sdu.java.server.controllers;

import cn.edu.sdu.java.server.payload.request.DataRequest;
import cn.edu.sdu.java.server.payload.response.DataResponse;
import cn.edu.sdu.java.server.services.CompetitionService;
import cn.edu.sdu.java.server.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/competition")

public class CompetitionController {
    private final CompetitionService competitionService;
    public CompetitionController(CompetitionService competitionService) {
        this.competitionService = competitionService;
    }
    @PostMapping("/getCompetitionList")
    public DataResponse getCompetitionList(@Valid @RequestBody DataRequest dataRequest) {
        return competitionService.getCompetitionList(dataRequest);
    }

    @PostMapping("/competitionSave")
    public DataResponse competitionSave(@Valid @RequestBody DataRequest dataRequest) {
        return competitionService.competitionSave(dataRequest);
    }
    @PostMapping("/competitionDelete")
    public DataResponse competitionDelete(@Valid @RequestBody DataRequest dataRequest) {
        return competitionService.competitionDelete(dataRequest);
    }
}
