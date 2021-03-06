<h1>Popvie - All about movie</h1>

<p>Created for Udacity Advanced Android Nanodegree Program - Project 2, Stage 1</p>

<h2 id="project-overview">Project Overview</h2>
<p>Most of us can relate to kicking back on the couch and enjoying a movie with friends and family. In this project, I built an app to allow users to discover the most popular, top rated, now playing and upcoming movies. Development of this app is splitted in two stages. This is <strong>Stage 1</strong>.</p>
<p>In this stage I built the core experience of Popvie app.</p>
<p>Popvie will:</p>
<ul>
<li>Present the user with a grid arrangement of movie posters upon launch.</li>
<li>Allow user to change sort order via either bottom navigation bar or option menu:
<ul>
<li>There are four different sort orders: by most popular, by top-rated, by now playing and by upcoming</li>
</ul>
</li>
<li>Allow the user to tap on a movie poster and transition to a details screen with additional information such as:<br />
<ul>
<li>movie poster image</li>
<li>original title</li>
<li>A plot synopsis (called overview in the api)</li>
<li>user rating (called vote_average in the api)</li>
<li>popularity</li>
<li>runtime</li>
<li>genre</li>
<li>tagline</li>
<li>release date</li>
<li>official site</li>
<li>budget</li>
<li>revenue</li>
<li>production countries</li>
<li>languages</li>
<li>production companies</li>
</ul>
</li>
</ul>
<p>&nbsp;</p>
<h2 id="why-this-project-">Why this Project?</h2>
<p>To become an Android developer, you must know how to bring particular mobile experiences to life. Specifically, you need to know how to build clean and compelling user interfaces (UIs), fetch data from network services, and optimize the experience for various mobile devices. You will hone these fundamental skills in this project.</p>
<p>By building this app, you will demonstrate your understanding of the foundational elements of programming for Android. Your app will communicate with the Internet and provide a responsive and delightful user experience.</p>
<p>&nbsp;</p>
<h2 id="what-will-i-learn-after-stage-1-">What did I learn after Stage 1?</h2>
<ul>
<li>Fetch data from the Internet with theMovieDB API.</li>
<li>Use adapters and custom list layouts to populate list views.</li>
<li>Incorporate libraries to simplify the amount of code you need to write</li>
<li>Design UI for different screen orientations (portrait and landscape)</li>
<li>Creating custom actionbar</li>
<li>Creating GridLayout with dynamic span count changes with screen density</li>
</ul>
<p>&nbsp;</p>
<h2>Setup before running</h2>
<p>Change API_KEY inside gradle.properties to be able to connect and fetch data.</p>
<blockquote>
<p><em>#API KEY for TMDB access TheMovieDBApiKey="xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"</em></p>
</blockquote>
<p>&nbsp;</p>
<h2>Dependencies</h2>
<ul style="list-style-type: circle;">
<li>'com.android.support:appcompat-v7:27.1.0'</li>
<li>'com.android.support:cardview-v7:27.1.0'</li>
<li>'com.android.support:design:27.1.0'</li>
<li>'com.android.support.constraint:constraint-layout:1.0.2'</li>
<li>'com.android.support:transition:27.1.0'</li>
<li>'com.squareup.retrofit2:retrofit:2.3.0'</li>
<li>'com.squareup.retrofit2:converter-gson:2.3.0'</li>
<li>'com.squareup.picasso:picasso:2.5.2'</li>
</ul>
<p>&nbsp;</p>
<h2 id="project-overview">Popvie App Screenshots</h2>
<p><img src="Popvie App - Screenshots Minified.jpg" alt="Popvie App Screenshots" /></p>

<h2>License</h2>
<p><strong>Source code license:</strong> https://github.com/akndmr/PopvieApp/blob/master/LICENSE</p>
<p>&nbsp;</p>
<p><strong>Popvie logo &amp; Popvie character license:</strong></p>
<a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-nd/4.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-nd/4.0/">Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International License</a>.

<h2>Icon Credits</h2>
Icons used for this project provided by <a href="https://github.com/akndmr/adbaicons">AdbA Icons</a>.
