insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 100, 0, 0, 0, 0
from celebrity cross join question
where question.text = 'Handelt es sich um eine fiktive Figur?'
and celebrity.name in ('Donald F. Duck', 'R2D2', 'Data', 'Sherlock Holmes', 'James Bond');
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 100, 0, 0, 0
from celebrity cross join question
where question.text = 'Handelt es sich um eine fiktive Figur?'
and celebrity.name not in ('Donald F. Duck', 'R2D2', 'Data', 'Sherlock Holmes', 'James Bond');
--
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 100, 0, 0, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur weiblich?'
and celebrity.name in (
'Audrey Hepburn',
'Jessica Lowndes',
'Shenae Grimes-Beech',
'Shania Twain',
'Ada Lovelace',
'Joyce Jonathan'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 100, 0, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur weiblich?'
and celebrity.name in (
'Steve Jobs',
'Keanu Reeves',
'Donald F. Duck',
'Albert Einstein',
'Daniel Glattauer',
'Raymond Radiguet',
'Gottlieb Daimler',
'Sherlock Holmes',
'James Bond',
'Sean Connery',
'William Shakespeare',
'Alan Turing',
'Konrad Zuse',
'Charles Babbage',
'Neil Armstrong'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 70, 20, 10, 0
from celebrity cross join question
where question.text = 'Ist die Figur weiblich?'
and celebrity.name in (
'R2D2',
'Data'
);
--
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 100, 0, 0, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur menschlich?'
and celebrity.name in (
'Steve Jobs',
'Audrey Hepburn',
'Keanu Reeves',
'Albert Einstein',
'Daniel Glattauer',
'Raymond Radiguet',
'Gottlieb Daimler',
'Jessica Lowndes',
'Shenae Grimes-Beech',
'Shania Twain',
'Sherlock Holmes',
'James Bond',
'Sean Connery',
'William Shakespeare',
'Alan Turing',
'Konrad Zuse',
'Charles Babbage',
'Ada Lovelace',
'Joyce Jonathan',
'Neil Armstrong'
);

insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 100, 0, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur menschlich?'
and celebrity.name in (
'Donald F. Duck',
'R2D2'
);

insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 3, 97, 0, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur menschlich?'
and celebrity.name in (
'Data'
);
--
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 100, 0, 0, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur vor dem Jahr 2000 verstorben?'
and celebrity.name in (
'Audrey Hepburn',
'Albert Einstein',
'Raymond Radiguet',
'Gottlieb Daimler',
'William Shakespeare',
'Alan Turing',
'Charles Babbage',
'Ada Lovelace'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 100, 0, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur vor dem Jahr 2000 verstorben?'
and celebrity.name in (
'Steve Jobs',
'Keanu Reeves',
'Donald F. Duck',
'Data',
'Daniel Glattauer',
'Jessica Lowndes',
'Shenae Grimes-Beech',
'Shania Twain',
'James Bond',
'Sean Connery',
'Joyce Jonathan',
'Neil Armstrong'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 30, 50, 20, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur vor dem Jahr 2000 verstorben?'
and celebrity.name in (
'Sherlock Holmes'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 70, 5, 15, 5, 5
from celebrity cross join question
where question.text = 'Ist die Figur vor dem Jahr 2000 verstorben?'
and celebrity.name in (
'Konrad Zuse'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 20, 10, 70, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur vor dem Jahr 2000 verstorben?'
and celebrity.name in (
'R2D2'
);
--
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 100, 0, 0, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur aus Kino oder Fernsehen bekannt?'
and celebrity.name in (
'Audrey Hepburn',
'Keanu Reeves',
'Donald F. Duck',
'R2D2',
'Data',
'Jessica Lowndes',
'Shenae Grimes-Beech',
'James Bond',
'Sean Connery'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 100, 0, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur aus Kino oder Fernsehen bekannt?'
and celebrity.name in (
'Steve Jobs',
'Albert Einstein',
'Daniel Glattauer',
'Raymond Radiguet',
'Gottlieb Daimler',
'William Shakespeare',
'Alan Turing',
'Konrad Zuse',
'Charles Babbage',
'Ada Lovelace',
'Neil Armstrong'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 90, 10, 0, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur aus Kino oder Fernsehen bekannt?'
and celebrity.name in (
'Sherlock Holmes'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 70, 0, 0, 30, 0
from celebrity cross join question
where question.text = 'Ist die Figur aus Kino oder Fernsehen bekannt?'
and celebrity.name in (
'Shania Twain'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 20, 0, 10, 70
from celebrity cross join question
where question.text = 'Ist die Figur aus Kino oder Fernsehen bekannt?'
and celebrity.name in (
'Joyce Jonathan'
);
--
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 100, 0, 0, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur für ihre Musik bekannt?'
and celebrity.name in (
'Shania Twain',
'Joyce Jonathan'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 100, 0, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur für ihre Musik bekannt?'
and celebrity.name not in (
'Shania Twain',
'Joyce Jonathan'
);
--
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 100, 0, 0, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur Wissenschaftler?'
and celebrity.name in (
'Albert Einstein',
'Alan Turing',
'Konrad Zuse',
'Charles Babbage',
'Ada Lovelace'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 100, 0, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur Wissenschaftler?'
and celebrity.name in (
'Steve Jobs',
'Audrey Hepburn',
'R2D2',
'Keanu Reeves',
'Donald F. Duck',
'Daniel Glattauer',
'Raymond Radiguet',
'Jessica Lowndes',
'Shenae Grimes-Beech',
'Shania Twain',
'James Bond',
'Sean Connery',
'William Shakespeare',
'Joyce Jonathan'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 30, 30, 30, 10, 0
from celebrity cross join question
where question.text = 'Ist die Figur Wissenschaftler?'
and celebrity.name in (
'Data'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 70, 20, 0, 10, 0
from celebrity cross join question
where question.text = 'Ist die Figur Wissenschaftler?'
and celebrity.name in (
'Gottlieb Daimler'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 10, 80, 10, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur Wissenschaftler?'
and celebrity.name in (
'Sherlock Holmes',
'Neil Armstrong'
);
--
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 100, 0, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur Politiker?'
and celebrity.name in (
'Steve Jobs',
'Audrey Hepburn',
'Keanu Reeves',
'Donald F. Duck',
'Albert Einstein',
'R2D2',
'Data',
'Daniel Glattauer',
'Raymond Radiguet',
'Gottlieb Daimler',
'Jessica Lowndes',
'Shenae Grimes-Beech',
'Shania Twain',
'Sherlock Holmes',
'James Bond',
'Sean Connery',
'William Shakespeare',
'Alan Turing',
'Konrad Zuse',
'Charles Babbage',
'Ada Lovelace',
'Joyce Jonathan',
'Neil Armstrong'
);
--
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 100, 0, 0, 0
from celebrity cross join question
where question.text = 'Hat die Figur etwas mit Sport zu tun?'
and celebrity.name in (
'Steve Jobs',
'Audrey Hepburn',
'Keanu Reeves',
'Donald F. Duck',
'Albert Einstein',
'R2D2',
'Data',
'Daniel Glattauer',
'Raymond Radiguet',
'Gottlieb Daimler',
'Jessica Lowndes',
'Shenae Grimes-Beech',
'Shania Twain',
'Sherlock Holmes',
'James Bond',
'Sean Connery',
'William Shakespeare',
'Alan Turing',
'Konrad Zuse',
'Charles Babbage',
'Ada Lovelace',
'Joyce Jonathan',
'Neil Armstrong'
);
--
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 100, 0, 0, 0
from celebrity cross join question
where question.text = 'Kann die Figur fliegen?'
and celebrity.name in (
'Steve Jobs',
'Audrey Hepburn',
'Keanu Reeves',
'Albert Einstein',
'R2D2',
'Data',
'Daniel Glattauer',
'Raymond Radiguet',
'Gottlieb Daimler',
'Jessica Lowndes',
'Shenae Grimes-Beech',
'Shania Twain',
'Sherlock Holmes',
'James Bond',
'Sean Connery',
'William Shakespeare',
'Alan Turing',
'Konrad Zuse',
'Charles Babbage',
'Ada Lovelace',
'Joyce Jonathan'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 30, 50, 10, 10, 0
from celebrity cross join question
where question.text = 'Kann die Figur fliegen?'
and celebrity.name in (
'Donald F. Duck'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 5, 95, 0, 0, 0
from celebrity cross join question
where question.text = 'Kann die Figur fliegen?'
and celebrity.name in (
'Neil Armstrong'
);
--
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 100, 0, 0, 0, 0
from celebrity cross join question
where question.text = 'Wird die Figur hin und wieder mit einem Apfel in Verbindung gebracht?'
and celebrity.name in (
'Steve Jobs'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 100, 0, 0, 0
from celebrity cross join question
where question.text = 'Wird die Figur hin und wieder mit einem Apfel in Verbindung gebracht?'
and celebrity.name in (
'Audrey Hepburn',
'Keanu Reeves',
'Donald F. Duck',
'Albert Einstein',
'R2D2',
'Data',
'Daniel Glattauer',
'Raymond Radiguet',
'Gottlieb Daimler',
'Jessica Lowndes',
'Shenae Grimes-Beech',
'Shania Twain',
'Sherlock Holmes',
'James Bond',
'Sean Connery',
'William Shakespeare',
'Konrad Zuse',
'Charles Babbage',
'Ada Lovelace',
'Joyce Jonathan',
'Neil Armstrong'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 5, 90, 5, 0, 0
from celebrity cross join question
where question.text = 'Wird die Figur hin und wieder mit einem Apfel in Verbindung gebracht?'
and celebrity.name in (
'Alan Turing'
);
--
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 100, 0, 0, 0, 0
from celebrity cross join question
where question.text = 'Wurde die Figur in Europa geboren?'
and celebrity.name in (
'Audrey Hepburn',
'Albert Einstein',
'Daniel Glattauer',
'Raymond Radiguet',
'Gottlieb Daimler',
'Sherlock Holmes',
'James Bond',
'Sean Connery',
'William Shakespeare',
'Alan Turing',
'Konrad Zuse',
'Charles Babbage',
'Ada Lovelace',
'Joyce Jonathan'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 100, 0, 0, 0
from celebrity cross join question
where question.text = 'Wurde die Figur in Europa geboren?'
and celebrity.name in (
'Steve Jobs',
'Keanu Reeves',
'R2D2',
'Data',
'Jessica Lowndes',
'Shenae Grimes-Beech',
'Shania Twain',
'Neil Armstrong'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 80, 10, 0, 10
from celebrity cross join question
where question.text = 'Wurde die Figur in Europa geboren?'
and celebrity.name in (
'Donald F. Duck'
);
--
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 100, 0, 0, 0, 0
from celebrity cross join question
where question.text = 'Wurde die Figur in den USA geboren?'
and celebrity.name in (
'Steve Jobs',
'Neil Armstrong'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 100, 0, 0, 0
from celebrity cross join question
where question.text = 'Wurde die Figur in den USA geboren?'
and celebrity.name in (
'Audrey Hepburn',
'Albert Einstein',
'R2D2',
'Data',
'Daniel Glattauer',
'Raymond Radiguet',
'Gottlieb Daimler',
'Sherlock Holmes',
'James Bond',
'Sean Connery',
'William Shakespeare',
'Alan Turing',
'Konrad Zuse',
'Charles Babbage',
'Ada Lovelace',
'Joyce Jonathan'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 80, 0, 10, 10, 0
from celebrity cross join question
where question.text = 'Wurde die Figur in den USA geboren?'
and celebrity.name in (
'Donald F. Duck'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 75, 15, 5, 5, 0
from celebrity cross join question
where question.text = 'Wurde die Figur in den USA geboren?'
and celebrity.name in (
'Keanu Reeves'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 15, 80, 5, 0, 0
from celebrity cross join question
where question.text = 'Wurde die Figur in den USA geboren?'
and celebrity.name in (
'Shania Twain'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 10, 75, 10, 0, 5
from celebrity cross join question
where question.text = 'Wurde die Figur in den USA geboren?'
and celebrity.name in (
'Jessica Lowndes',
'Shenae Grimes-Beech'
);
--
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 100, 0, 0, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur nach 1950 geboren?'
and celebrity.name in (
'Steve Jobs',
'Keanu Reeves',
'Data',
'Jessica Lowndes',
'Shenae Grimes-Beech',
'Shania Twain',
'Joyce Jonathan'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 100, 0, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur nach 1950 geboren?'
and celebrity.name in (
'Audrey Hepburn',
'Donald F. Duck',
'Albert Einstein',
'Raymond Radiguet',
'Gottlieb Daimler',
'Sherlock Holmes',
'Sean Connery',
'William Shakespeare',
'Alan Turing',
'Konrad Zuse',
'Charles Babbage',
'Ada Lovelace',
'Neil Armstrong'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 75, 25, 0, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur nach 1950 geboren?'
and celebrity.name in (
'R2D2'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 90, 0, 10, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur nach 1950 geboren?'
and celebrity.name in (
'Daniel Glattauer'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 80, 20, 0, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur nach 1950 geboren?'
and celebrity.name in (
'James Bond'
);
--
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 100, 0, 0, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur weltweit bekannt?'
and celebrity.name in (
'Steve Jobs',
'Audrey Hepburn',
'Keanu Reeves',
'Donald F. Duck',
'Albert Einstein',
'R2D2',
'Data',
'Gottlieb Daimler',
'Jessica Lowndes',
'Shenae Grimes-Beech',
'Shania Twain',
'Sherlock Holmes',
'James Bond',
'Sean Connery',
'William Shakespeare',
'Alan Turing',
'Konrad Zuse',
'Charles Babbage',
'Ada Lovelace',
'Neil Armstrong'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 10, 60, 30, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur weltweit bekannt?'
and celebrity.name in (
'Daniel Glattauer'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 70, 10, 20, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur weltweit bekannt?'
and celebrity.name in (
'Raymond Radiguet'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 90, 10, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur weltweit bekannt?'
and celebrity.name in (
'Joyce Jonathan'
);
--
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 100, 0, 0, 0, 0
from celebrity cross join question
where question.text = 'Trägt die Figur eine Waffe?'
and celebrity.name in (
'James Bond'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 100, 0, 0, 0
from celebrity cross join question
where question.text = 'Trägt die Figur eine Waffe?'
and celebrity.name in (
'Steve Jobs',
'Audrey Hepburn',
'Albert Einstein',
'R2D2',
'Daniel Glattauer',
'Raymond Radiguet',
'Gottlieb Daimler',
'Shania Twain',
'William Shakespeare',
'Alan Turing',
'Konrad Zuse',
'Charles Babbage',
'Ada Lovelace',
'Joyce Jonathan',
'Neil Armstrong'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 15, 85, 0, 0, 0
from celebrity cross join question
where question.text = 'Trägt die Figur eine Waffe?'
and celebrity.name in (
'Keanu Reeves',
'Sean Connery'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 75, 25, 0, 0, 0
from celebrity cross join question
where question.text = 'Trägt die Figur eine Waffe?'
and celebrity.name in (
'Data'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 5, 95, 0, 0, 0
from celebrity cross join question
where question.text = 'Trägt die Figur eine Waffe?'
and celebrity.name in (
'Donald F. Duck',
'Sherlock Holmes'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 95, 5, 0, 0
from celebrity cross join question
where question.text = 'Trägt die Figur eine Waffe?'
and celebrity.name in (
'Jessica Lowndes',
'Shenae Grimes-Beech'
);
--
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 100, 0, 0, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur hauptsächlich durch eine Fernsehserie bekannt?'
and celebrity.name in (
'Data'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 100, 0, 0, 0
from celebrity cross join question
where question.text = 'Ist die Figur hauptsächlich durch eine Fernsehserie bekannt?'
and celebrity.name in (
'Steve Jobs',
'Audrey Hepburn',
'Keanu Reeves',
'Donald F. Duck',
'Albert Einstein',
'R2D2',
'Daniel Glattauer',
'Raymond Radiguet',
'Gottlieb Daimler',
'Shania Twain',
'Sherlock Holmes',
'James Bond',
'Sean Connery',
'William Shakespeare',
'Alan Turing',
'Konrad Zuse',
'Charles Babbage',
'Ada Lovelace',
'Joyce Jonathan',
'Neil Armstrong'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 85, 0, 5, 10, 0
from celebrity cross join question
where question.text = 'Ist die Figur hauptsächlich durch eine Fernsehserie bekannt?'
and celebrity.name in (
'Jessica Lowndes',
'Shenae Grimes-Beech'
);
--
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 100, 0, 0, 0, 0
from celebrity cross join question
where question.text = 'War oder ist die Figur verheiratet?'
and celebrity.name in (
'Steve Jobs',
'Audrey Hepburn',
'Albert Einstein',
'Shania Twain',
'James Bond',
'Sean Connery'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 100, 0, 0, 0
from celebrity cross join question
where question.text = 'War oder ist die Figur verheiratet?'
and celebrity.name in (
'Donald F. Duck',
'R2D2',
'Data',
'Raymond Radiguet',
'Sherlock Holmes',
'Alan Turing',
'Joyce Jonathan'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 0, 30, 70, 0
from celebrity cross join question
where question.text = 'War oder ist die Figur verheiratet?'
and celebrity.name in (
'Gottlieb Daimler',
'Konrad Zuse',
'Shenae Grimes-Beech',
'Charles Babbage',
'Ada Lovelace',
'Neil Armstrong'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 0, 100, 0, 0
from celebrity cross join question
where question.text = 'War oder ist die Figur verheiratet?'
and celebrity.name in (
'Keanu Reeves',
'Daniel Glattauer',
'Jessica Lowndes'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 80, 0, 15, 5, 0
from celebrity cross join question
where question.text = 'War oder ist die Figur verheiratet?'
and celebrity.name in (
'William Shakespeare'
);
--
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 0, 100, 0, 0
from celebrity cross join question
where question.text = 'Hat die Figur eine Katze als Haustier?'
and celebrity.name in (
'Steve Jobs',
'Keanu Reeves',
'Donald F. Duck',
'Albert Einstein',
'R2D2',
'Daniel Glattauer',
'Raymond Radiguet',
'Gottlieb Daimler',
'Jessica Lowndes',
'Shenae Grimes-Beech',
'Shania Twain',
'Sherlock Holmes',
'James Bond',
'Sean Connery',
'William Shakespeare',
'Alan Turing',
'Konrad Zuse',
'Charles Babbage',
'Ada Lovelace',
'Joyce Jonathan',
'Neil Armstrong'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 5, 0, 95, 0, 0
from celebrity cross join question
where question.text = 'Hat die Figur eine Katze als Haustier?'
and celebrity.name in (
'Audrey Hepburn'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 80, 5, 15, 0, 0
from celebrity cross join question
where question.text = 'Hat die Figur eine Katze als Haustier?'
and celebrity.name in (
'Data'
);
--
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 100, 0, 0, 0, 0
from celebrity cross join question
where question.text = 'War die Figur bereits im Weltall?'
and celebrity.name in (
'R2D2',
'Data',
'Neil Armstrong'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 100, 0, 0, 0
from celebrity cross join question
where question.text = 'War die Figur bereits im Weltall?'
and celebrity.name in (
'Steve Jobs',
'Audrey Hepburn',
'Keanu Reeves',
'Donald F. Duck',
'Albert Einstein',
'Daniel Glattauer',
'Raymond Radiguet',
'Gottlieb Daimler',
'Jessica Lowndes',
'Shenae Grimes-Beech',
'Shania Twain',
'Sherlock Holmes',
'Sean Connery',
'William Shakespeare',
'Alan Turing',
'Konrad Zuse',
'Charles Babbage',
'Ada Lovelace',
'Joyce Jonathan'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 95, 3, 2, 0, 0
from celebrity cross join question
where question.text = 'War die Figur bereits im Weltall?'
and celebrity.name in (
'James Bond'
);
--
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 100, 0, 0, 0
from celebrity cross join question
where question.text = 'War die Figur bereits wegen Drogenproblemen in den Medien?'
and celebrity.name in (
'Steve Jobs',
'Audrey Hepburn',
'Donald F. Duck',
'Albert Einstein',
'R2D2',
'Data',
'Daniel Glattauer',
'Raymond Radiguet',
'Gottlieb Daimler',
'Shania Twain',
'Sherlock Holmes',
'James Bond',
'Sean Connery',
'William Shakespeare',
'Alan Turing',
'Konrad Zuse',
'Charles Babbage',
'Ada Lovelace',
'Joyce Jonathan',
'Neil Armstrong'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 80, 5, 0, 15
from celebrity cross join question
where question.text = 'War die Figur bereits wegen Drogenproblemen in den Medien?'
and celebrity.name in (
'Keanu Reeves',
'Jessica Lowndes',
'Shenae Grimes-Beech'
);
--
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 100, 0, 0, 0, 0
from celebrity cross join question
where question.text = 'Fängt der Name (Vorname oder Künstlername) mit einem Vokal an?'
and upper(substr(celebrity.name, 1, 1)) in ('A', 'E', 'I', 'O', 'U');
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 100, 0, 0, 0
from celebrity cross join question
where question.text = 'Fängt der Name (Vorname oder Künstlername) mit einem Vokal an?'
and upper(substr(celebrity.name, 1, 1)) not in ('A', 'E', 'I', 'O', 'U');
--
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 10, 60, 10, 10, 10
from celebrity cross join question
where question.text = 'Hat man die Figur oft auf Pferden gesehen?'
and celebrity.name in (
'Audrey Hepburn',
'Keanu Reeves'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 100, 0, 0, 0
from celebrity cross join question
where question.text = 'Hat man die Figur oft auf Pferden gesehen?'
and celebrity.name in (
'Steve Jobs',
'Donald F. Duck',
'Albert Einstein',
'R2D2',
'Data',
'Daniel Glattauer',
'Raymond Radiguet',
'Gottlieb Daimler',
'Jessica Lowndes',
'Shenae Grimes-Beech',
'Shania Twain',
'Sherlock Holmes',
'James Bond',
'Sean Connery',
'William Shakespeare',
'Alan Turing',
'Konrad Zuse',
'Joyce Jonathan',
'Neil Armstrong'
);
insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no)
select celebrity.id, question.id, 0, 0, 70, 30, 0
from celebrity cross join question
where question.text = 'Hat man die Figur oft auf Pferden gesehen?'
and celebrity.name in (
'Charles Babbage',
'Ada Lovelace'
);
/*
select 'insert into knowledge_base (celebrity_id, question_id, yes, no, dunno, probably_yes, probably_no) values ('
|| celebrity_id || ', '
|| question_id || ', '
|| yes || ', '
|| no || ', '
|| dunno || ', '
|| probably_yes || ', '
|| probably_no || ');'
from knowledge_base
*/
