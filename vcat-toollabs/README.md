vcat-toollabs
=============

Configuration file
------------------

The finished program needs a configuration file. This is an example of the
properties that need to be set in this file:

	# Cache directory for Graphviz and image files
	cache.dir=./cache
	
	# Purge caches after (seconds)
	purge=600
	# Purge metadata after (seconds)
	purge.metadata=86400
	
	# JDBC URL and login information for MySQL/MariaDB access to wiki table
	jdbc.url=jdbc:mysql://enwiki.labsdb:3306/meta_p
	jdbc.user=username_for_db
	jdbc.password=password_for_db
	
	# Redis server information
	redis.server.hostname=localhost
	#redis.server.port=6379
	
	# Redis secret used for prefix
	redis.secret=1234567890
	
	# Redis channel suffixes
	redis.channel.control.suffix=-control
	redis.channel.request.suffix=-requests
	redis.channel.response.suffix=-responses
	
	# Redis key suffixes
	redis.key.request.suffix=-request
	redis.key.response.suffix=-response
	redis.key.response.error.suffix=-response-error
	redis.key.response.headers.suffix=-response-headers