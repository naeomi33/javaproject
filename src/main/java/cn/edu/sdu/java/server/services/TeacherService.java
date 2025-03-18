package cn.edu.sdu.java.server.services;

import cn.edu.sdu.java.server.models.Person;
import cn.edu.sdu.java.server.models.Student;
import cn.edu.sdu.java.server.models.Teacher;
import cn.edu.sdu.java.server.payload.request.DataRequest;
import cn.edu.sdu.java.server.payload.response.DataResponse;
import cn.edu.sdu.java.server.repositorys.TeacherRepository;
import cn.edu.sdu.java.server.util.ComDataUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    public Map<String,Object> getMapFromTeacher(Teacher t) {
        Map<String,Object> m = new HashMap<>();
        Person p;
        if(t == null)
            return m;
        m.put("title",t.getTitle());
        m.put("degree",t.getDegree());
        p = t.getPerson();
        if(p == null)
            return m;
        m.put("personId", t.getPersonId());
        m.put("num",p.getNum());
        m.put("name",p.getName());
        m.put("dept",p.getDept());
        m.put("card",p.getCard());
        String gender = p.getGender();
        m.put("gender",gender);
        m.put("genderName", ComDataUtil.getInstance().getDictionaryLabelByValue("XBM", gender)); //性别类型的值转换成数据类型名
        m.put("birthday", p.getBirthday());  //时间格式转换字符串
        m.put("email",p.getEmail());
        m.put("phone",p.getPhone());
        m.put("address",p.getAddress());
        return m;
    }
    public List<Map<String,Object>> getTeacherList(String numName) {
        List<Map<String,Object>> dataList = new ArrayList<>();
        List<Teacher> sList = teacherRepository.findTeacherListByNumName(numName);  //数据库查询操作
        if (sList == null || sList.isEmpty())
            return dataList;
        for (Teacher student : sList) {
            dataList.add(getMapFromTeacher(student));
        }
        return dataList;
    }


    public DataResponse teacherDelete(DataRequest dataRequest) {
        return null;
    }

    public DataResponse getTeacherInfo(DataRequest dataRequest) {
        Integer teacherId = dataRequest.getInteger("teacherId");
        Teacher teacher = teacherRepository.findById(teacherId).orElse(null);
        if(teacher == null)
            return new DataResponse(404,"","教师不存在");
        Map<String,Object> data = getMapFromTeacher(teacher);
        return new DataResponse(200,data,"");
    }

    public DataResponse teacherEditSave(DataRequest dataRequest) {
        return null;
    }

    public DataResponse getTeacherList(DataRequest dataRequest) {
        String numName = dataRequest.getString("numName");
        List<Map<String,Object>> dataList = getTeacherList(numName);
        return new DataResponse(200,dataList,"");
    }
}
