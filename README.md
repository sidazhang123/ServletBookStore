# ServletBookStore
A group assignment for comp9321. It was basically developed in pure Java + Servlet but also got exposed to hibernate and c3p0.

Overall architecture:
DB <-> c3p0 connection pool <-> DAO + domain/model <-> business layer <-> service <-> controller + view <-> intercepter/filter <-> user

Detailed explaination and performance analysis(jmeter) are in GroupReport-2-24.pdf.
The performance was acceptable when 1400 users visited simultaneously in terms of median RTT(250ms) and server utilization(82%).


This web app functions as a typical online shopping mall:
 - register/login
 - list products
 - search products
 - shopping cart
 - make orders
 - merge orders
 - email notification
 

Account privileges:
admin - edit listing products, alter orders, ban users, view activity traffics and a visualized user-product relationship network.
customer - all the common functions listed above.

![eer](https://user-images.githubusercontent.com/31929318/38487049-45da7090-3c22-11e8-87bc-35046ad7f0fc.png)
