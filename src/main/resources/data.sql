insert into accounts(id, account_id, password, user_role)
values (1,
        'stipe123',
        '$2a$12$qcEk1rcWdaw.FJo4UfWEt.DQTJilCbGQt7YgWPFiJUKKQGd8sBe5W',
        'USER');

insert into accounts(id, account_id, password, user_role)
values (2,
        'marija98',
        '$2a$12$b88IcoFVurfEKOjm7TCGZ.KjCXscW.Lz.Su/Oy0r/Endy4nsU7/Pm',
        'USER');

insert into url(account_id, long_url, redirect_type, created_date, number_of_clicks)
values (1,
        'https://stackoverflow.com/questions/65296773/thymeleaf-extras-security-doesnt-work-with-spring-security',
        301,
        '2023-02-15',
        23);

insert into url(account_id, long_url, redirect_type, created_date, number_of_clicks)
values (1,
        'https://www.gitkraken.com/git-client?utm_feeditemid=&utm_device=c&utm_term=git%20gui%20for%20linux&utm_campaign=GK+Git+GUI+-+Search+(EN)&utm_source=google&utm_medium=ppc&hsa_acc=1130375851&hsa_cam=18519961346&hsa_grp=145767943567&hsa_ad=626305507908&hsa_src=g&hsa_tgt=kwd-347748752156&hsa_kw=git%20gui%20for%20linux&hsa_mt=b&hsa_net=adwords&hsa_ver=3&gclid=CjwKCAiA_6yfBhBNEiwAkmXy59T2FyHgNTcdzIf3kq8elcr2sU5I9Np6JUbwHBBdT3QoHRy4SzG1IhoC6egQAvD_BwE',
        302,
        '2023-02-14',
        12);

insert into url(account_id, long_url, redirect_type, created_date, number_of_clicks)
values (1,
        'https://www.24sata.hr/sport/nogometni-spektakl-bayern-kod-psg-a-mbappe-na-klupi-891686',
        301,
        '2023-02-14',
        47);

insert into url(account_id, long_url, redirect_type, created_date, number_of_clicks)
values (2,
        'https://www.gloria.hr/gl/fashion-and-beauty/inspiracija/visoke-cizme-koje-nosi-katie-holmes-isplati-se-imati-u-ormaru-15305802',
        301,
        '2023-02-11',
        19);

insert into url(account_id, long_url, redirect_type, created_date, number_of_clicks)
values (2,
        'https://stackoverflow.com/questions/65296773/thymeleaf-extras-security-doesnt-work-with-spring-security',
        302,
        '2023-02-12',
        16);