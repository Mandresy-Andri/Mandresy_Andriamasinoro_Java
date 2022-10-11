use sakila;

-- Total Rental Amount
select f.film_id, sum(amount) as total_amount from film f
inner join inventory i 
on f.film_id = i.film_id
inner join rental r
on i.inventory_id = r.inventory_id
inner join payment p
on r.rental_id = p.rental_id
group by f.film_id;

-- Total Rental Amount View
create view total_rental_amount as
select f.film_id, sum(amount) as total_amount from film f
inner join inventory i 
on f.film_id = i.film_id
inner join rental r
on i.inventory_id = r.inventory_id
inner join payment p
on r.rental_id = p.rental_id
group by f.film_id;

-- Total Film Copies
select film_id, count(film_id) as total_copies from inventory
group by film_id;

-- Total Film Copies View
create view total_film_copies as
select film_id, count(film_id) as total_copies from inventory
group by film_id;

-- Films with Greater than 200.00 Total Rental Amount
select f.film_id, f.title, f.description, 
		f.rental_duration, f.rental_rate, f.replacement_cost, 
        f.rating, tc.total_copies, ta.total_amount from film f
inner join total_rental_amount ta
on ta.film_id = f.film_id
inner join total_film_copies tc
on tc.film_id = f.film_id
where total_amount>200;
