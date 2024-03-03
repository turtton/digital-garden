import { Head, Html, Main, NextScript } from "next/document";
import Script from "next/script";

export default function Document(): JSX.Element {
	return (
		<Html>
			<Head>
				<Script src="/theme.js" strategy="beforeInteractive" />
				<link rel="preconnect" href="https://fonts.googleapis.com" />
				<link
					rel="preconnect"
					href="https://fonts.gstatic.com"
					crossOrigin="use-credentials"
				/>
				<link
					href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@300;400;500;600;700;800&display=swap"
					rel="stylesheet"
				/>
			</Head>
			<body>
				<Main />
				<NextScript />
			</body>
		</Html>
	);
}
