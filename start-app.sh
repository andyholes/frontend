echo "MySQL Username: "
read mysql_username
echo "MySQL Password: "
read mysql_password

if ! sudo mysql -u "$mysql_username" -p"$mysql_password" -e "SELECT 1" > /dev/null 2>&1; then
  echo "MySQL server is not running or credentials are incorrect."
  exit 1
fi

if ! sudo mysql -u "$mysql_username" -p"$mysql_password" -e "SHOW DATABASES LIKE 'ensolvers'" | grep -qw "ensolvers"; then
  echo "Creating database 'ensolvers'..."
  sudo mysql -u "$mysql_username" -p"$mysql_password" -e "CREATE DATABASE ensolvers"
fi

cd backend
./mvnw spring-boot:run &
sleep 20

cd ../frontend
if [ ! -d "node_modules/axios" ]; then
  echo "Axios is not installed. Installing..."
  npm install axios
fi

npm start

echo "My Notes App started successfully!"
