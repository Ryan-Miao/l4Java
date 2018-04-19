package com.test.java.stream;

import com.google.common.collect.Lists;
import com.test.java.entity.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.junit.Before;
import org.junit.Test;

public class DistinctTest {

  private List<User> users;

  @Before
  public void setUp() {
    users = Lists.newArrayList(
        new User(1, "a"),
        new User(1, "b"),
        new User(2, "b"),
        new User(1, "a"));
  }

  @Test
  public void demo() {
    users.parallelStream().distinct().forEach(System.out::println);
  }

  @Test
  public void dis1() {
    List<User> result = new LinkedList<>();
    for (User user : users) {
      boolean b = result.stream().anyMatch(u -> u.getId().equals(user.getId()));
      if (!b) {
        result.add(user);
      }
    }

    System.out.println(result);
  }

  @Test
  public void dis2() {
    Set<User> result = new HashSet<>(users);
    System.out.println(result);
  }

  @Test
  public void dis3() {
    users.parallelStream().filter(distinctByKey(User::getId))
        .forEach(System.out::println);
  }

  private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
    Set<Object> seen = ConcurrentHashMap.newKeySet();
    return t -> seen.add(keyExtractor.apply(t));
  }
}
