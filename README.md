#
docker run --name parking-db -p 5432:5432 -e POSTGRES_DB=parking -e POSTGRES_USER=adriano -e POSTGRES_PASSWORD=adr123 -d postgres:10-alpine