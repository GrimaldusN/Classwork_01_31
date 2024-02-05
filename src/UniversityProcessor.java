import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.FileReader;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class UniversityProcessor {

    // 1. Получить список всех курсов в университете
    public List<Course> getAllCourses(University university) {
        return university.getDepartments().stream()
                .flatMap(department -> department.getCourses().stream())
                .distinct()
                .toList();

    }

    // 2. Найти количество курсов в каждом департаменте
    public Map<String, Long> getCoursesCountByDepartment(University university) {
        return university.getDepartments().stream()
                .collect(Collectors.toMap(department -> department.getName(), count -> (long) count.getCourses().size()));

    }

    // 3. Получить список названий всех курсов, которые имеют более 3 кредитов
    public List<String> getCoursesWithMoreThanThreeCredits(University university) {
        return university.getDepartments().stream()
                .flatMap(department -> department.getCourses().stream())
                .filter(course -> course.getCredits() > 3)
                .map(course -> course.getTitle())
                .toList();
    }

    // 4. Найти всех профессоров, которые ведут более одного курса
    public Set<String> getProfessorsTeachingMultipleCourses(University university) {
        return university.getDepartments().stream()
                .flatMap(department -> department.getCourses().stream())
                .map(course -> course.getProfessor())
                .collect(Collectors.toSet());
    }

    // 5. Получить мапу курсов, где ключ - название курса, значение - список тем
    public Map<String, List<String>> getCourseTopicsMap(University university) {
        return university.getDepartments().stream()
                .flatMap(department -> department.getCourses().stream())
                .collect(Collectors.toMap(course -> course.getTitle(), value -> value.getTopics()));
    }

    // 6. Найти департаменты, где все курсы имеют более 3 кредитов
    public List<String> getDepartmentsWithAllCoursesMoreThanThreeCredits(University university) {
        return university.getDepartments().stream()
                .flatMap(department -> department.getCourses().stream())
                .filter(course -> course.getCredits()>3)
                .map(course -> course.getTitle())
                .toList();
    }

    // 7. Получить список курсов, сгруппированных по количеству кредитов
    public Map<Integer, List<Course>> getCoursesGroupedByCredits(University university) {
        return university.getDepartments().stream()
                .collect(Collectors.toMap(key -> key.getCourses().stream()
                        .map(course -> course.getCredits())
                        .reduce(Integer::compareTo)
                        .get(), value -> value.getCourses()));
    }

    // 8. Найти департамент с самым большим количеством курсов
//    public String getDepartmentWithMostCourses(University university) {
//        return university.getDepartments().stream()
//                .map(department -> department.getCourses().size())
//                .max(Integer::compareTo)
//                .get();
//    } не понимаю как решить задачу так чтобы вывести название

    // 9. Получить мапу, где ключ - название департамента, а значение - среднее количество кредитов курсов в департаменте
    public Map<String, Double> getAverageCreditsPerDepartment(University university) {
        return null;
    }

    // 10. Найти курсы, у которых более 2 тем и профессор начинается на 'Dr.'
    public List<Course> getCoursesWithMoreThanTwoTopicsAndDrProfessor(University university) {
        return null;
    }

}
