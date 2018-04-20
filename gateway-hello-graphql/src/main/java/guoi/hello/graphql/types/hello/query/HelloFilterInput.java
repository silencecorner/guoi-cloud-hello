package guoi.hello.graphql.types.hello.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HelloFilterInput {
    //  # logical operators
    private List<HelloFilterInput> AND;//    AND: [HelloFilterInput!] # combines all passed `HelloFilterInput` objects with logical AND
    private List<HelloFilterInput> OR;//    OR: [HelloFilterInput!] # combines all passed `HelloFilterInput` objects with logical OR
    //
    //  # ID filters
    private String id;//    id: ID # matches all nodes with exact value
    private String id_not;//    id_not: ID # matches all nodes with different value
    private List<String> id_in;//    id_in: [ID!] # matches all nodes with value in the passed list
    private List<String> id_not_in;//    id_not_in: [ID!] # matches all nodes with value not in the passed list
    private String id_lt;//    id_lt: ID # matches all nodes with lesser value
    private String id_lte;//    id_lte: ID # matches all nodes with lesser or equal value
    private String id_gt;//    id_gt: ID # matches all nodes with greater value
    private String id_gte;//    id_gte: ID # matches all nodes with greater or equal value
    private String id_contains;//    id_contains: ID # matches all nodes with a value that contains given substring
    private String id_not_contains;//    id_not_contains: ID # matches all nodes with a value that does not contain given substring
    private String id_starts_with;//    id_starts_with: ID # matches all nodes with a value that starts with given substring
    private String id_not_starts_with;//    id_not_starts_with: ID # matches all nodes with a value that does not start with given substring
    private String id_ends_with;//    id_ends_with: ID # matches all nodes with a value that ends with given substring
    private String id_not_ends_with;//    id_not_ends_with: ID # matches all nodes with a value that does not end with given substring
    //

    //  # createdAt DateTime filters
    private Date createdAt;//    createdAt: DateTime # matches all nodes with exact value
    private Date createdAt_not;//    createdAt_not: DateTime # matches all nodes with different value
    private List<Date> createdAt_in;//    createdAt_in: [DateTime!] # matches all nodes with value in the passed list
    private List<Date> createdAt_not_in;//    createdAt_not_in: [DateTime!] # matches all nodes with value not in the passed list
    private Date createdAt_lt;//    createdAt_lt: DateTime # matches all nodes with lesser value
    private Date createdAt_lte;//    createdAt_lte: DateTime # matches all nodes with lesser or equal value
    private Date createdAt_gt;//    createdAt_gt: DateTime # matches all nodes with greater value
    private Date createdAt_gte;//    createdAt_gte: DateTime # matches all nodes with greater or equal value
    //
    //  # updatedAt DateTime filters
    private Date updatedAt;//    updatedAt: DateTime # matches all nodes with exact value
    private Date updatedAt_not;//    updatedAt_not: DateTime # matches all nodes with different value
    private List<Date> updatedAt_in;//    updatedAt_in: [DateTime!] # matches all nodes with value in the passed list
    private List<Date> updatedAt_not_in;//    updatedAt_not_in: [DateTime!] # matches all nodes with value not in the passed list
    private Date updatedAt_lt;//    updatedAt_lt: DateTime # matches all nodes with lesser value
    private Date updatedAt_lte;//    updatedAt_lte: DateTime # matches all nodes with lesser or equal value
    private Date creaupdatedAt_gttedAt;//    updatedAt_gt: DateTime # matches all nodes with greater value
    private Date updatedAt_gte;//    updatedAt_gte: DateTime # matches all nodes with greater or equal value
    //
    //
    //  # lastName String filters
    private String lastName;//    lastName: String # matches all nodes with exact value
    private String lastName_not;//    lastName_not: String # matches all nodes with different value
    private List<String> lastName_in;//    lastName_in: [String!] # matches all nodes with value in the passed list
    private List<String> lastName_not_in;//    lastName_not_in: [String!] # matches all nodes with value not in the passed list
    private String lastName_lt;//    lastName_lt: String # matches all nodes with lesser value
    private String lastName_lte;//    lastName_lte: String # matches all nodes with lesser or equal value
    private String lastName_gt;//    lastName_gt: String # matches all nodes with greater value
    private String lastName_gte;//    lastName_gte: String # matches all nodes with greater or equal value
    private String lastName_contains;//    lastName_contains: String # matches all nodes with a value that contains given substring
    private String lastName_not_contains;//    lastName_not_contains: String # matches all nodes with a value that does not contain given substring
    private String lastName_starts_with;//    lastName_starts_with: String # matches all nodes with a value that starts with given substring
    private String lastName_not_starts_with;//    lastName_not_starts_with: String # matches all nodes with a value that does not start with given substring
    private String lastName_ends_with;//    lastName_ends_with: String # matches all nodes with a value that ends with given substring
    private String lastName_not_ends_with;//    lastName_not_ends_with: String # matches all nodes with a value that does not end with given substring
}
