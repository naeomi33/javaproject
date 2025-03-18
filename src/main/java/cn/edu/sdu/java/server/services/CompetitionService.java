package cn.edu.sdu.java.server.services;

import cn.edu.sdu.java.server.models.Competition;
import cn.edu.sdu.java.server.payload.request.DataRequest;
import cn.edu.sdu.java.server.payload.response.DataResponse;
import cn.edu.sdu.java.server.repositorys.CompetitionRepository;
import cn.edu.sdu.java.server.util.CommonMethod;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CompetitionService {
    private final CompetitionRepository competitionRepository;
    public CompetitionService(CompetitionRepository competitionRepository) {
        this.competitionRepository = competitionRepository;
    }

    //增&改
    public DataResponse competitionSave(DataRequest dataRequest) {
        Integer competitionId = dataRequest.getInteger("competitionId");
        String name = dataRequest.getString("name");
        String time = dataRequest.getString("time");
        String place = dataRequest.getString("place");
        String description = dataRequest.getString("description");

        Optional<Competition> op;
        Competition c= null;

        if(competitionId != null) {
            op = competitionRepository.findById(competitionId);
            if(op.isPresent())
                c= op.get();
        }
        if(c== null)
            c = new Competition();

        c.setName(name);
        c.setTime(time);
        c.setPlace(place);
        c.setDescription(description);
        competitionRepository.save(c);
        return CommonMethod.getReturnMessageOK();
    }

    //删
    public DataResponse competitionDelete(DataRequest dataRequest) {
        Integer competitionId = dataRequest.getInteger("competitionId");
        Optional<Competition> op;
        Competition c = null;
        if (competitionId != null) {
            op = competitionRepository.findById(competitionId);
            if (op.isPresent()) {
                c = op.get();
                competitionRepository.delete(c);
            }
        }
        return CommonMethod.getReturnMessageOK();
    }

    //查
    public DataResponse getCompetitionList(DataRequest dataRequest) {
        List<Competition> competitions = competitionRepository.findAll();
        List<Map<String,Object>> list = new ArrayList<>();
        for(Competition c : competitions) {
            Map<String,Object> map = new HashMap<>();
            map.put("competitionId", c.getId());
            map.put("name", c.getName());
            map.put("time", c.getTime());
            map.put("place", c.getPlace());
            map.put("description", c.getDescription());
            list.add(map);
        }
        return CommonMethod.getReturnData(list);
    }

}
