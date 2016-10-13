# KanyeNets - My Beautiful Dark Twisted Triadic Closure
### NETS 150 Final Project
### Daniel Moreno, Serena Tsay, Connor Swords, Connor Wen

Our final project for NETS 150 at the University of Pennsylvania is broken down into two parts: 

## Twitter and Album Lyric Analysis 

We used concepts from information retrieval and document search to scrape Kanye West's twitter feed as well as a lyrics database for all of Kanye’s song lyrics. To scrape Kanye’s Twitter profile, we used the Twitter4j library. For the database, we made calls to a RESTful API. We then used the Vector Space Model to compare tweets and album lyrics, and album lyrics with other album lyrics.

## Grammy Network Analysis 
We also used web scraping methods to collect all of Kanye’s collaborators for his Grammy nominations. We then created a graph of collaborations using our custom graph implementation and then we ran a neighborhood overlap analysis on the data to look for cliques and other features.

The [pdf](/My Beautiful Dark Twisted Triadic Closure.pdf) outlines the analyses and discoveries made in analyzing our model.

![cover](/Cover.png)
