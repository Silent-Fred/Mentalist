# Mentalist
20 Questions game - educational style.

How to guess a celebrity by asking 20 questions.

## What does _educational style_ mean?
The source is intended to focus on some rather specific topics to be shown
and discussed in class. Whatever distracts from this focus was considered
less important, even though it might be important in another context. (e.g.
unit tests are not included, some game functionality is not implemented,
localization is missing, error handling is rather sloppy...)

## Necessary adjustments
You might want to change the `spring.datasource.url` in the `application.properties`
file if:
* you're not lucky enough to have the same first name or 
* you don't use it as a username on your computer or
* you just want to have the database files stored somewhere else

You might also consider to use `spring.jpa.hibernate.ddl-auto=update`
if you want to explore a growing knowledge base. The default setting
is meant to lead to reproducible results that can be compared between
runs (e.g. with different questioning strategies - cf. `@Qualifier` for
`questionnaire` in `GameApiController`)
